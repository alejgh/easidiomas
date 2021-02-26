import React, { useContext } from 'react';
import { AppContext } from '../App';
import {
  StyleSheet,
  Text,
  View,
  Image,
  TouchableOpacity
} from 'react-native';


export default function ProfileScreen(props) {

    const {parentNavigation,navigation,isOwner} = props;
    const {avatar,name,surname,username,learning,speaks} = props.user;
    const context = useContext(AppContext);
   
    const editProfile = function(){
      navigation.navigate('Edit Profile');
    }

    const logOut = function(){
      context.setUser(null);
    }

    const sendMessage = function(){
      parentNavigation.navigate('Chats',{startChat:true,user:props.user});
    }


    return (
      <View style={styles.container}>
          <View style={styles.header}></View>
          <Image style={styles.avatar} source={{uri: avatar}}/>
          <View style={styles.bodyContent}>
            <Text style={styles.nameLabel} >{name}{' '}{surname}</Text>
            <Text style={styles.usernameLabel}>{username}</Text>
            
            <View style={styles.languajesContainer}>
              <View style={styles.learningContainer}>
                <Text style={styles.learningLabel}>Native</Text>
                <Text style={styles.learningLabel}>{speaks.toUpperCase()}</Text>
              </View>
              <View style={styles.learningContainer}>
                <Text style={styles.learningLabel}>Learning</Text>
                <Text style={styles.learningLabel}>{learning[0].toUpperCase()+'-'+learning[1].toUpperCase()}</Text>
              </View>
            </View>
            {
            isOwner ?
              <View>
                  <TouchableOpacity style={styles.buttonContainer} onPress={()=> editProfile()}>
                      <Text style={styles.usernameLabel}>Edit Profile</Text>  
                  </TouchableOpacity>      
                  <TouchableOpacity style={styles.buttonContainer} onPress={()=> logOut()}>
                      <Text style={styles.usernameLabel}>Log Out</Text>  
                  </TouchableOpacity>
                </View>  
              :
              <TouchableOpacity style={styles.buttonContainer} onPress={()=> sendMessage()}>
                <Text style={styles.usernameLabel}>Send Message</Text>  
              </TouchableOpacity>      
            }
          </View>
      </View>
    );
}

const styles = StyleSheet.create({
    container:{
      flex: 1,
      backgroundColor:'#1b2836'
    },
  header:{
    backgroundColor: "#1DA1F2",
    height:200,
  },
  avatar: {
    width: 130,
    height: 130,
    borderRadius: 63,
    borderWidth: 4,
    borderColor: "white",
    marginBottom:10,
    alignSelf:'center',
    position: 'absolute',
    marginTop:130
  },
  name:{
    fontSize:22,
    color:"#FFFFFF",
    fontWeight:'600',
    backgroundColor:'#435060'
  },
  body:{
    backgroundColor:'red',
    marginTop:40,
  },
  bodyContent: {
    marginTop:50,
    alignItems: 'center',
    padding:30,
  },
  name:{
    fontSize:28,
    color: "#696969",
    fontWeight: "600"
  },
  info:{
    fontSize:16,
    color: "#00BFFF",
    marginTop:10
  },
  description:{
    fontSize:16,
    color: "#696969",
    marginTop:10,
    textAlign: 'center',
  },
  buttonContainer: {
    height:45,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom:20,
    width:250,
    borderRadius:30,
    backgroundColor: "#1DA1F2"
  },
  nameLabel:{
    fontSize:25,
    color:'#fff'
  },
  usernameLabel:{
    fontSize:18,
    color:'#fff'
  },
  languajesContainer:{
    width:300,
    marginTop:20,
    flexDirection:'row',
    justifyContent:'space-around',
    marginBottom:40
  },
  learningContainer:{
    alignItems:'center'
  },
  learningLabel:{
    fontSize:20,
    color:'#fff'
  }
});


