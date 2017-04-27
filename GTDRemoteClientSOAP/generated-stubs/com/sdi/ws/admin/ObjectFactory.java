
package com.sdi.ws.admin;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sdi.ws.admin package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BusinessException_QNAME = new QName("http://admin.impl.business.sdi.com/", "BusinessException");
    private final static QName _DisableUser_QNAME = new QName("http://admin.impl.business.sdi.com/", "disableUser");
    private final static QName _FindUserById_QNAME = new QName("http://admin.impl.business.sdi.com/", "findUserById");
    private final static QName _DeepDeleteUser_QNAME = new QName("http://admin.impl.business.sdi.com/", "deepDeleteUser");
    private final static QName _EnableUser_QNAME = new QName("http://admin.impl.business.sdi.com/", "enableUser");
    private final static QName _FindAllUsersResponse_QNAME = new QName("http://admin.impl.business.sdi.com/", "findAllUsersResponse");
    private final static QName _FindAllUsersInfo_QNAME = new QName("http://admin.impl.business.sdi.com/", "findAllUsersInfo");
    private final static QName _DisableUserResponse_QNAME = new QName("http://admin.impl.business.sdi.com/", "disableUserResponse");
    private final static QName _FindUserByIdResponse_QNAME = new QName("http://admin.impl.business.sdi.com/", "findUserByIdResponse");
    private final static QName _FindAllUsersInfoResponse_QNAME = new QName("http://admin.impl.business.sdi.com/", "findAllUsersInfoResponse");
    private final static QName _DeepDeleteUserResponse_QNAME = new QName("http://admin.impl.business.sdi.com/", "deepDeleteUserResponse");
    private final static QName _FindAllUsers_QNAME = new QName("http://admin.impl.business.sdi.com/", "findAllUsers");
    private final static QName _EnableUserResponse_QNAME = new QName("http://admin.impl.business.sdi.com/", "enableUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sdi.ws.admin
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DisableUser }
     * 
     */
    public DisableUser createDisableUser() {
        return new DisableUser();
    }

    /**
     * Create an instance of {@link EnableUser }
     * 
     */
    public EnableUser createEnableUser() {
        return new EnableUser();
    }

    /**
     * Create an instance of {@link FindAllUsersResponse }
     * 
     */
    public FindAllUsersResponse createFindAllUsersResponse() {
        return new FindAllUsersResponse();
    }

    /**
     * Create an instance of {@link DisableUserResponse }
     * 
     */
    public DisableUserResponse createDisableUserResponse() {
        return new DisableUserResponse();
    }

    /**
     * Create an instance of {@link FindAllUsersInfo }
     * 
     */
    public FindAllUsersInfo createFindAllUsersInfo() {
        return new FindAllUsersInfo();
    }

    /**
     * Create an instance of {@link DeepDeleteUserResponse }
     * 
     */
    public DeepDeleteUserResponse createDeepDeleteUserResponse() {
        return new DeepDeleteUserResponse();
    }

    /**
     * Create an instance of {@link FindAllUsersInfoResponse }
     * 
     */
    public FindAllUsersInfoResponse createFindAllUsersInfoResponse() {
        return new FindAllUsersInfoResponse();
    }

    /**
     * Create an instance of {@link BusinessException }
     * 
     */
    public BusinessException createBusinessException() {
        return new BusinessException();
    }

    /**
     * Create an instance of {@link DeepDeleteUser }
     * 
     */
    public DeepDeleteUser createDeepDeleteUser() {
        return new DeepDeleteUser();
    }

    /**
     * Create an instance of {@link FindUserById }
     * 
     */
    public FindUserById createFindUserById() {
        return new FindUserById();
    }

    /**
     * Create an instance of {@link FindUserByIdResponse }
     * 
     */
    public FindUserByIdResponse createFindUserByIdResponse() {
        return new FindUserByIdResponse();
    }

    /**
     * Create an instance of {@link FindAllUsers }
     * 
     */
    public FindAllUsers createFindAllUsers() {
        return new FindAllUsers();
    }

    /**
     * Create an instance of {@link EnableUserResponse }
     * 
     */
    public EnableUserResponse createEnableUserResponse() {
        return new EnableUserResponse();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "BusinessException")
    public JAXBElement<BusinessException> createBusinessException(BusinessException value) {
        return new JAXBElement<BusinessException>(_BusinessException_QNAME, BusinessException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "disableUser")
    public JAXBElement<DisableUser> createDisableUser(DisableUser value) {
        return new JAXBElement<DisableUser>(_DisableUser_QNAME, DisableUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "findUserById")
    public JAXBElement<FindUserById> createFindUserById(FindUserById value) {
        return new JAXBElement<FindUserById>(_FindUserById_QNAME, FindUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeepDeleteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "deepDeleteUser")
    public JAXBElement<DeepDeleteUser> createDeepDeleteUser(DeepDeleteUser value) {
        return new JAXBElement<DeepDeleteUser>(_DeepDeleteUser_QNAME, DeepDeleteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnableUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "enableUser")
    public JAXBElement<EnableUser> createEnableUser(EnableUser value) {
        return new JAXBElement<EnableUser>(_EnableUser_QNAME, EnableUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "findAllUsersResponse")
    public JAXBElement<FindAllUsersResponse> createFindAllUsersResponse(FindAllUsersResponse value) {
        return new JAXBElement<FindAllUsersResponse>(_FindAllUsersResponse_QNAME, FindAllUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsersInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "findAllUsersInfo")
    public JAXBElement<FindAllUsersInfo> createFindAllUsersInfo(FindAllUsersInfo value) {
        return new JAXBElement<FindAllUsersInfo>(_FindAllUsersInfo_QNAME, FindAllUsersInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "disableUserResponse")
    public JAXBElement<DisableUserResponse> createDisableUserResponse(DisableUserResponse value) {
        return new JAXBElement<DisableUserResponse>(_DisableUserResponse_QNAME, DisableUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "findUserByIdResponse")
    public JAXBElement<FindUserByIdResponse> createFindUserByIdResponse(FindUserByIdResponse value) {
        return new JAXBElement<FindUserByIdResponse>(_FindUserByIdResponse_QNAME, FindUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsersInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "findAllUsersInfoResponse")
    public JAXBElement<FindAllUsersInfoResponse> createFindAllUsersInfoResponse(FindAllUsersInfoResponse value) {
        return new JAXBElement<FindAllUsersInfoResponse>(_FindAllUsersInfoResponse_QNAME, FindAllUsersInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeepDeleteUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "deepDeleteUserResponse")
    public JAXBElement<DeepDeleteUserResponse> createDeepDeleteUserResponse(DeepDeleteUserResponse value) {
        return new JAXBElement<DeepDeleteUserResponse>(_DeepDeleteUserResponse_QNAME, DeepDeleteUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "findAllUsers")
    public JAXBElement<FindAllUsers> createFindAllUsers(FindAllUsers value) {
        return new JAXBElement<FindAllUsers>(_FindAllUsers_QNAME, FindAllUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnableUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://admin.impl.business.sdi.com/", name = "enableUserResponse")
    public JAXBElement<EnableUserResponse> createEnableUserResponse(EnableUserResponse value) {
        return new JAXBElement<EnableUserResponse>(_EnableUserResponse_QNAME, EnableUserResponse.class, null, value);
    }

}
