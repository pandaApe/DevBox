package com.pa.devbox.domain.entity.rest;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 21/5/16 16:30.
 * Email: whailong2010@gmail.com
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Auth {

    private AuthData authData;

    public AuthData getAuthData() {
        return authData;
    }

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }

    /*
     {
      "authData": {
        "qq": {
          "openid": "0395BA18A5CD6255E5BA185E7BEBA242",
          "access_token": "12345678-SaMpLeTuo3m2avZxh5cjJmIrAfx4ZYyamdofM7IjU",
          "expires_in": 1382686496
          }
     }
     }'
     */
    @JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
    public static class AuthData {
        private QQAuth qq;

        public QQAuth getQq() {
            return qq;
        }

        public AuthData setQq(QQAuth qq) {
            this.qq = qq;
            return this;
        }
    }


}
