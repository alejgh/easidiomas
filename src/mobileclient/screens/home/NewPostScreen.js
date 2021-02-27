import React, { useEffect,useContext,useState } from 'react';
import { StyleSheet, TextInput , View, Image } from 'react-native';
import {HomeContext} from '../navigation/HomeStackNavigator';

export default function NewPostScreen(props) {

  const context = useContext(HomeContext);
  const {parentNavigation} = props;
  const [value,setValue] = useState(null);

  const onChangeText = function(text){
    setValue(text);
    context.setNewPost(text)
  }

  useEffect(()=>{
    parentNavigation.setOptions({
        tabBarVisible: false
    });

    return () => {
        parentNavigation.setOptions({
            tabBarVisible: true
        });
    }
},[])

  return (
    <View style={styles.container}>
       <View style={styles.avatarContainer}>
          <Image style={styles.photo} source={{ uri: 'https://reactnative.dev/img/tiny_logo.png'}}/>
       </View>
       <View style={styles.inputContainer}>
        <TextInput
              style={styles.input}
              onChangeText={text => onChangeText(text)}
              value={value}
              autoFocus
              multiline
              placeholder='What´s happening?'
              placeholderTextColor='#AAB8C2'
          />
       </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    flexDirection:'row'
  },
  avatarContainer:{
    flex: 0.18,
    backgroundColor:'transparent'
  },
  inputContainer:{
    flex: 0.85,
    backgroundColor:'transparent',
    paddingTop:20,
    paddingLeft:10,
    paddingRight:30,
  },
  input:{
    height:'100%',
    textAlignVertical:'top',
    fontSize:20,
    color:'#fff'
  },
  photo: {
    width: 50,
    height: 50,
    borderRadius: 50,
    marginTop: 10,
    marginLeft: 10
  }
});
