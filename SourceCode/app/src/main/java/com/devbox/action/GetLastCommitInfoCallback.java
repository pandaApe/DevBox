package com.devbox.action;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 9/2/16 20:23.
 * @Email: whailong2010@gmail.com
 */
public abstract class GetLastCommitInfoCallback implements HttpCallback<String> {

    public void done(String committerName, String commitDate, String msgStr, AppException e) {
    }

    @Override
    public void done(String data, AppException e) {
        
    }
}
