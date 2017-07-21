package interfacePlatform.controller;

import interfacePlatform.dto.Person;
import interfacePlatform.model.UserModel;
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
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zsh
 */

@Controller
@RequestMapping(value="/user/")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private IUserService userService;

    @RequestMapping(value="getAllUser",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getAllUser(HttpServletRequest request){
        List<UserModel> list = userService.getAllUserModel();
        if (list!=null) return new AjaxResult(ResponseCode.success.getCode(),ResponseCode.success.getMsg(),request.getRequestURI(),list.size());
        return new AjaxResult(ResponseCode.success.getCode(),ResponseCode.success.getMsg(),request.getRequestURI(),0);
    }

//
//    @RequestMapping(value="/getUser",method = RequestMethod.GET)
//    public void getUser(String id,HttpServletRequest request){
//
//        request.setAttribute("user", userService.getUserModel(id));
//
//    }
    //http://localhost:8080/interfacePlatform/user/addUser?age=22&userName=olooo
    @RequestMapping(value="/addUser",method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult addUser(UserModel user,HttpServletRequest request){
        userService.addUserModel(user);
        return new AjaxResult(ResponseCode.success.getCode(),ResponseCode.success.getMsg(),request.getRequestURI(),"");

    }
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
    public Person getUrl(Person person){
        return person;
    }

}
























































