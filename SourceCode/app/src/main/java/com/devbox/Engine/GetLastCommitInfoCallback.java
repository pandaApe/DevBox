package com.devbox.Engine;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 19/2/16 21:26.
 * @Email: whailong2010@gmail.com
 */
public abstract class GetLastCommitInfoCallback extends HttpCallback<String> {
    public void done(String committerName, String commitDate, String msgStr, AppException e) {
    }
}
