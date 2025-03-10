package com.manager.doc.dao.user.information.fetch.full;

import com.manager.doc.dto.user.UserWithInformationDTO;
import com.manager.doc.model.user.User;

import java.util.List;

public interface FetchFullUserDao {

    List<UserWithInformationDTO> getFullUserInformation();
    List<UserWithInformationDTO> getFullPrivateUserInformation();
    List<User> getFullUserAndAccount();

}
