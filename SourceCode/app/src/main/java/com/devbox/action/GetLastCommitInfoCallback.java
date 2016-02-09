package com.devbox.action;

/**
 * Description:
 *
 * @Author: PandaApe.
 * @CreatedAt: 9/2/16 20:23.
 * @Email: whailong2010@gmail.com
 */
public interface GetLastCommitInfoCallback extends HttpCallback<String> {

    void onSuccess(String committerName, String commitDate, String msgStr);
}
