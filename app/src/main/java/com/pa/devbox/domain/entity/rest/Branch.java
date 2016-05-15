package com.pa.devbox.domain.entity.rest;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 15/5/16 19:34.
 * Email: whailong2010@gmail.com
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Branch {

    private String name;

    private CommitInfo commit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommitInfo getCommit() {
        return commit;
    }

    public void setCommit(CommitInfo commit) {
        this.commit = commit;
    }

    @JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
    public static class CommitInfo {
        private String sha;
        private String url;

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
