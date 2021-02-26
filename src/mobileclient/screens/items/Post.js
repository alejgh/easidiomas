import React,{useContext, useState} from 'react';
import {
  StyleSheet,
  View,
  Text,
  Image,
  
  TouchableHighlight,
  TouchableOpacity, 
} from 'react-native'
import EvilIcons from 'react-native-vector-icons/EvilIcons'
import Entypo from 'react-native-vector-icons/Entypo'
import {AppContext} from '../../App';

export default function Post(props){

    const context = useContext(AppContext);
    const {parentNavigation,content,numLikes} = props
    const {id,name,username,avatar} = props.user;
    const [photo,setPhoto] = useState({ uri: avatar});
    const [touched,setTouched] = useState(false);
    const [likes,setLikes] = useState(numLikes);
    const [time,setTime] = useState('1hr');
    const [liked,setLiked] = useState(false);


    const postPressed = function(pressed = false){
      setTouched(pressed)
    }

    const like = function(pressed = false){
      if (liked){ 
          setLiked(false)
          setLikes(likes-1)
        }else{
          setLiked(true)
          setLikes(likes+1)
        }
    }

    const navigateToProfile = function(){
      fetch('http://localhost:5000/api/mock/user'+id)
      .then((response) => response.json())
      .then((data) =>{
        let isOwner = data.id==context.user.id ? true :false;
        console.log(data)
        console.log(isOwner)
        parentNavigation.navigate("Profile",{user:data,isOwner:isOwner});
      } )
      .catch((error) => console.error(error))
     
    }

    return(
      <TouchableHighlight onPressIn={() => postPressed(true)} onPressOut={() => postPressed()}>
        <View style={styles.container}>
            <View style={styles.innerContainer}>
              <View style={styles.photoContainer}>
                <View style={styles.innerPhotoContainer}>
                  <TouchableOpacity onPress={navigateToProfile}>
                  <Image
                    source={photo}
                    style={styles.photo}/>
                  </TouchableOpacity>
                </View>
              </View>
              <View style={styles.info}>
                <View style={styles.userDetails}>
                  <Text style={styles.userName}>{name}
                    <Text style={styles.userHandleAndTime}>{username} · {time}</Text>
                  </Text>
                </View>
              <View style={styles.postTextContainer}>
                <Text style={styles.postText}>{content}</Text>

              </View>
              <View style={styles.postActionsContainer}>
               
                <TouchableOpacity onPress={()=> like()}  style={styles.likeButton}>
                { liked ? 
                  <Entypo name={'heart'} size={18} style={{marginLeft:4}} color={liked ? "rgb(224, 36, 94)" : 'rgb(136, 153, 166)'}/>
                  :
                  <EvilIcons name={'heart'} size={25} color={liked ? "rgb(224, 36, 94)" : 'rgb(136, 153, 166)'}/>
                
                }
                <Text style={[styles.likeButtonIcon, {color: liked ? "rgb(224, 36, 94)" : "rgb(136, 153, 166)",fontWeight: liked ? "bold" : "300",}]}>{likes}</Text>
                </TouchableOpacity>
              </View>
              
              </View>
            </View>
        </View>
       </TouchableHighlight>
    )
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    borderBottomColor: "#435060",
    borderBottomWidth: 0.5,
    flexDirection: "column",
    backgroundColor: "#1b2836"
  },
  innerContainer: {
    flex: 1,
    borderColor: "green",
    flexDirection: "row",
    borderWidth: 0,
    height: "auto"
  },
  photoContainer: {
    flex: 0.23,
    borderColor: "yellow",
    flexDirection: "column",
    borderWidth: 0
  },
  innerPhotoContainer: { height: 100, alignItems: "center" },
  photo: {
    width: 50,
    height: 50,
    borderRadius: 50,
    marginTop: 10
  },
  info: {
    flex: 0.77,
    borderColor: "yellow",
    flexDirection: "column",
    borderWidth: 0,
    marginTop: 10
  },
  userDetails: {
    flex: 1,
    borderColor: "blue",
    borderWidth: 0,
    marginBottom: 5
  },
  userName: { color: "white", fontWeight: "bold" },
  userHandleAndTime: {
    color: "rgb(136, 153, 166)",
    marginLeft: 5
  },
  postTextContainer: { flex: 1, borderColor: "blue", borderWidth: 0 },
  postText: { color: "white", paddingRight: 10 },
  postActionsContainer: {
    flex: 1,
    borderColor: "blue",
    borderWidth: 0,
    marginTop: 4,
    flexDirection: "row-reverse",
    paddingBottom: 5
  },
  likeButton: {
    padding: 5,
    flex: 0.25,
    alignItems: "center",
    flexDirection: "row",
    borderColor: "red",
    borderWidth: 0
  },
  likeButtonIcon: {
    position: "absolute",
    left: 27,
    marginLeft: 3
  }
});

