//package org.itstep.service.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class CustomDetailsService implements UserDetailsService {

//    @Autowired
//    UserGalleryRepository userGalleryRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//        Student student = studentRepository.findUserByStudentName(userName);
//        Teacher teacher = teacherRepository.findUserByTeacherName(userName);
//        if(student != null){
//            return studentRepository.findUserByStudentName(userName);
//        }else if(teacher != null){
//            return teacherRepository.findUserByTeacherName(userName);
//        }else {
//            return new User("admin","$2a$10$qxcV1Da7Hnfi5vv/eRp8HuYKlmiw6NBaPBJJff5vDkFR.K1fiBoBm",
//                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
//        }
//
//    }
//}
