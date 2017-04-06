package com.sdi.business.impl.user;

import javax.ejb.Remote;

import com.sdi.business.services.UserService;

@Remote
public interface RemoteUserService extends UserService {

}
