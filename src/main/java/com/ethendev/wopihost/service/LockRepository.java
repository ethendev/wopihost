package com.ethendev.wopihost.service;

import com.ethendev.wopihost.entity.LockInfo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * save and update the file lock info from wopi client
 *
 * @author ethendev
 * @date 2019/10/27
 */
@Service
public class LockRepository {

    private Map<String, LockInfo> map = new ConcurrentHashMap<>();

    public boolean save(String fileId, LockInfo lockInfo) {
        map.put(fileId, lockInfo);
        return true;
    }

    public boolean update(String fileId, LockInfo lockInfo) {
        if (map.get(fileId) != null) {
            map.put(fileId, lockInfo);
        } else {
            return false;
        }
        return true;
    }

    public LockInfo getLockInfo(String fileId) {
        return map.get(fileId);
    }

    public boolean delete(String fileId) {
        if (map.get(fileId) != null) {
            map.remove(fileId);
        } else {
            return false;
        }
        return true;
    }

}
