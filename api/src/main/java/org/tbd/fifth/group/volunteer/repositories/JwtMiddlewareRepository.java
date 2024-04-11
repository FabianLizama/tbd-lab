package org.tbd.fifth.group.volunteer.repositories;

import org.tbd.fifth.group.volunteer.models.UserModel;

public interface JwtMiddlewareRepository {
    public String generateToken(UserModel userModel);
    public Boolean validateToken(String token);

    public UserModel decodeJWT(String token);


}
