package com.ethendev.wopihost.service;

import com.ethendev.wopihost.controller.WopiHostController;
import com.ethendev.wopihost.entity.LockInfo;
import com.ethendev.wopihost.entity.WopiRequestHeader;
import com.ethendev.wopihost.entity.WopiResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Handle lock requests
 *
 * @author ethendev
 * @date 2019/10/27
 */
@Service
public class WopiLockService {

    @Autowired
    private LockRepository lockRepository;

    private static final String EMPTY_STRING = "";

    private Logger logger = LoggerFactory.getLogger(WopiHostController.class);

    /**
     * Processes a Lock request
     *
     * @param request
     * @param fileName
     * @return
     */
    public ResponseEntity lock(HttpServletRequest request, String fileName) {
        String requestLock = request.getHeader(WopiRequestHeader.LOCK);
        LockInfo lockInfo = lockRepository.getLockInfo(fileName);
        // Ensure the file isn't already locked or expired
        if (lockInfo == null || StringUtils.isEmpty(lockInfo.getLockValue())) {
            lockInfo = new LockInfo(requestLock, LocalDateTime.now());
            lockRepository.save(fileName, lockInfo);
            return ResponseEntity.ok().build();
        } else if (lockInfo.isExpired() || lockInfo.getLockValue().equals(requestLock)) {
            // Update the file with a LockValue and expiration,
            // or refresh lock by extending expiration when lock matches existing lock
            lockInfo.setLockValue(requestLock);
            lockInfo.setCreatedAt(LocalDateTime.now());
            lockRepository.update(fileName, lockInfo);
            return ResponseEntity.ok().build();
        }
        return setLockMismatch(lockInfo.getLockValue(), "File already locked by another interface");
    }

    /**
     * Processes a GetLock request
     *
     * @param request
     * @param fileName
     * @return
     */
    public ResponseEntity getLock(HttpServletRequest request, String fileName) {
        LockInfo lockInfo = lockRepository.getLockInfo(fileName);        // Check for valid lock on file
        if (lockInfo == null || StringUtils.isEmpty(lockInfo.getLockValue())) {
            return ResponseEntity.ok().header(WopiRequestHeader.LOCK, EMPTY_STRING).build();
        } else if (lockInfo.isExpired()) {
            // File lock expired, so clear it out
            lockRepository.delete(fileName);
            // File is not locked...return empty X-WOPI-Lock header
            return ResponseEntity.ok().header(WopiRequestHeader.LOCK, EMPTY_STRING).build();
        }
        // File has a valid lock, so we need to return it
        return ResponseEntity.ok().header(WopiRequestHeader.LOCK, lockInfo.getLockValue()).build();
    }

    /**
     * Processes a RefreshLock request
     *
     * @param request
     * @param fileName
     * @return
     */
    public ResponseEntity refreshLock(HttpServletRequest request, String fileName) {
        LockInfo lockInfo = lockRepository.getLockInfo(fileName);
        String requestLock = request.getHeader(WopiRequestHeader.LOCK);
        // Ensure the file has a valid lock
        if (lockInfo == null || StringUtils.isEmpty(lockInfo.getLockValue())) {
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (lockInfo.isExpired()) {
            // File lock expired, so clear it out
            lockRepository.delete(fileName);
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (!lockInfo.getLockValue().equals(requestLock)) {
            return setLockMismatch(lockInfo.getLockValue(), "Lock mismatch");
        } else {
            // Extend the expiration
            lockInfo.setCreatedAt(LocalDateTime.now());
            lockRepository.update(fileName, lockInfo);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Processes a Unlock request
     *
     * @param request
     * @param fileName
     * @return
     */
    public ResponseEntity unlock(HttpServletRequest request, String fileName) {
        LockInfo lockInfo = lockRepository.getLockInfo(fileName);
        String requestLock = request.getHeader(WopiRequestHeader.LOCK);
        // Ensure the file has a valid lock
        if (lockInfo == null || StringUtils.isEmpty(lockInfo.getLockValue())) {
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (lockInfo.isExpired()) {
            // File lock expired, so clear it out
            lockRepository.delete(fileName);
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (!lockInfo.getLockValue().equals(requestLock)) {
            return setLockMismatch(lockInfo.getLockValue(), "Lock mismatch");
        } else {
            // Unlock the file and return success 200
            lockRepository.delete(fileName);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Processes a UnlockAndRelock request
     *
     * @param request
     * @param fileName
     */
    public ResponseEntity unlockAndRelock(HttpServletRequest request, String fileName) {
        LockInfo lockInfo = lockRepository.getLockInfo(fileName);
        String requestLock = request.getHeader(WopiRequestHeader.LOCK);
        String requestOldLock = request.getHeader(WopiRequestHeader.OLD_LOCK);

        // Ensure the file has a valid lock
        if (lockInfo == null || StringUtils.isEmpty(lockInfo.getLockValue())) {
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (lockInfo.isExpired()) {
            // File lock expired, so clear it out
            lockRepository.delete(fileName);
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (!lockInfo.getLockValue().equals(requestOldLock)) {
            return setLockMismatch(lockInfo.getLockValue(), "Lock mismatch");
        } else {
            // Update the file with a LockValue and LockExpiration and return success 200
            lockInfo.setLockValue(requestLock);
            lockInfo.setCreatedAt(LocalDateTime.now());
            lockRepository.update(fileName, lockInfo);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Processes a PutFile request
     *
     * @param request
     * @param file
     * @param content
     * @return
     */
    public ResponseEntity putFile(HttpServletRequest request, File file, byte[] content) {
        String requestLock = request.getHeader(WopiRequestHeader.LOCK);
        LockInfo lockInfo = lockRepository.getLockInfo(file.getName());
        // Ensure the file has a valid lock
        if (lockInfo == null || StringUtils.isEmpty(lockInfo.getLockValue())) {
            // If the file is 0 bytes, this is document creation
            if (content.length == 0) {
                writeFile(file, content);
                return ResponseEntity.ok().build();
            } else {
                return setLockMismatch(EMPTY_STRING, "File isn't locked");
            }
        } else if (lockInfo.isExpired()) {
            // File lock expired, so clear it out
            lockRepository.delete(file.getName());
            return setLockMismatch(EMPTY_STRING, "File isn't locked");
        } else if (!lockInfo.getLockValue().equals(requestLock)) {
            return setLockMismatch(EMPTY_STRING, "Lock mismatch");
        }
        writeFile(file, content);
        return ResponseEntity.ok().build();
    }

    public void writeFile(File file, byte[] content) {
        try (FileOutputStream fop = new FileOutputStream(file)) {
            fop.write(content);
            fop.flush();
        } catch (IOException e) {
            logger.error("write file failed, errMsg: {}", e);
        }
    }

    private ResponseEntity setLockMismatch(String existingLock, String failReason) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(WopiResponseHeader.LOCK, existingLock);
        headers.set(WopiResponseHeader.LOCK_FAILURE_REASON, failReason);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("Lock mismatch/Locked by another interface");
    }

}
