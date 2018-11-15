package com.wang.shiro;

import com.wang.common.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author wp
 * @date 2018/11/14 10:42
 */
@Slf4j
public class MySessionDao extends AbstractSessionDAO {

    private static final long EXPIRE_TIME = 10 * 60;

    private static String prefix = "shiro-session:";

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sid = this.getSessionIdGenerator().generateId(session);
        // sid和session绑定
        super.assignSessionId(session, sid);
        log.info("创建sessionId = {}", sid.toString());
        redisUtil.set(prefix + sid, session, EXPIRE_TIME);
        return sid;
    }

    @Override
    protected Session doReadSession(Serializable sid) {
        Session session = (Session) redisUtil.get(prefix + sid.toString());
        if (session != null) {
            // 更新过期时间
            redisUtil.set(prefix + sid.toString(), session, EXPIRE_TIME);
        }
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("update session : {}", session.getId().toString());
        redisUtil.set(prefix + session.getId().toString(), session, EXPIRE_TIME);
    }

    @Override
    public void delete(Session session) {
        log.info("delete session : {}", session.getId().toString());
        redisUtil.del(prefix + session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return new ArrayList<Session>();
    }
}
