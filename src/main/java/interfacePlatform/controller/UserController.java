package interfacePlatform.controller;

import interfacePlatform.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import interfacePlatform.service.IUserService;
import interfacePlatform.core.dict.ResponseCode;
import interfacePlatform.dto.AjaxResult;

/**
 * @author zsh
 */

@Controller
@RequestMapping(value="/user/")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private IUserService userService;
//
//    @RequestMapping(value="getAllUser",method = RequestMethod.GET)
//    public void getAllUser(HttpServletRequest request){
//        logger.debug("");
//        request.setAttribute("userList",userService.getAllUserModel());
//        //这里的return相当于struts2中的execute中的返回值
//        logger.debug("userService.getAllUserModel():"+userService.getAllUserModel().size());
//    }
//
//    @RequestMapping(value="getAllUserWithIdAuto",method = RequestMethod.GET)
//    @ResponseBody
//    public AjaxResult getAllUserUserModelWithIdAuto(HttpServletRequest request){
//        logger.debug("");
//        request.setAttribute("userList",userService.getAllUserModelWithIdAuto());
//        //这里的return相当于struts2中的execute中的返回值
//        logger.debug("userService.getAllUserModel():"+userService.getAllUserModelWithIdAuto().size());
//        return new AjaxResult(ResponseCode.success.getCode(),ResponseCode.success.getMsg(),null, null);
//    }
//
//    @RequestMapping(value="/getUser",method = RequestMethod.GET)
//    public void getUser(String id,HttpServletRequest request){
//
//        request.setAttribute("user", userService.getUserModel(id));
//
//    }
//    @RequestMapping(value="/addUser",method = RequestMethod.GET)
//    public void addUser(UserModel user,HttpServletRequest request){
//        user.setAge("18");
//        System.out.println("user:"+user.getAge()+user.getId());
//        userService.addUserModel(user);
//    }
//    @RequestMapping(value="/addUserWithIdAuto",method = RequestMethod.GET)
//    public void addUser(UserModelWithIdAuto user,HttpServletRequest request){
//        user.setAge("18");
//        System.out.println("user:"+user.getAge()+user.getId());
//        userService.addUserModelWithIdAuto(user);
//    }
//    @RequestMapping(value="/toAddUser",method = RequestMethod.GET)
//    public void toAddUser(){
//    }
//    @RequestMapping(value="/delUser",method = RequestMethod.GET)
//    public void delUser(String id,HttpServletResponse response){
//
//        String result = "{\"result\":\"error\"}";
//
//        if(userService.delUserModel(id)){
//            result = "{\"result\":\"success\"}";
//        }
//
//        response.setContentType("application/json");
//
//        try {
//            PrintWriter out = response.getWriter();
//            out.write(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RequestMapping(value="/updateUser",method = RequestMethod.GET)
//    public void updateUser(UserModel user,HttpServletRequest request){
//        user.setId("3455e9d312f0421da58d952443c2fbd9");
//        user.setAge("19");
//        if(userService.updateUserModel(user)){
//            user = userService.getUserModel(user.getId());
//            request.setAttribute("user", user);
//        }else{
//        }
//    }

    //http://localhost:8080/user/getPerson
    @RequestMapping(value = "getPerson",method = RequestMethod.GET)
    @ResponseBody
    public Person getUrl(){
        Person person = new Person();
        person.setAge(18);
        return person;
    }
}
























































