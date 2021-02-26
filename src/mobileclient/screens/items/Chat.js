import React,{useState} from 'react';
import {
  StyleSheet,
  View,
  Text,
  Image,
  
  TouchableHighlight,
  TouchableOpacity, 
} from 'react-native'

export default function Chat(props){

    const {navigation,sreen} = props;
    const {name,username,avatar} = props.user;
    const [photo,setPhoto] = useState({ uri: avatar});
    const [time,setTime] = useState('1hr');

    const navigateToScreen = function(){
        navigation.navigate(sreen,{user:props.user,isOwner:false})
    }


    return(
      <TouchableHighlight onPress={() => navigateToScreen(true)} >
        <View style={styles.container}>
            <View style={styles.innerContainer}>
              <View style={styles.photoContainer}>
                <View style={styles.innerPhotoContainer}>
                  <Image
                    source={photo}
                    style={styles.photo}/>
                </View>
              </View>
              <View style={styles.info}>
                <View style={styles.userDetails}>
                  <Text style={styles.userName}>{name}
                    <Text style={styles.userHandleAndTime}>{username}</Text>
                  </Text>
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
  innerPhotoContainer: { height: 80, alignItems: "center" },
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
  }
});
