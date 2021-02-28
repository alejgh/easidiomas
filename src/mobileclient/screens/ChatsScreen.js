import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Chat from './items/Chat';

export default function ChatsScreen({route,navigation}) {

  const context = useContext(AppContext);
  const {REQUEST_URI} = context.CONFIG;
  const [chats, setChats] = useState([]);

  const getChats = async function(){
    let response = await (await fetch(REQUEST_URI+'/chats')).json();
    let newChats = [];
    for(let chat in response){
      let user1 = await getUser(response[chat].user1)
      let user2 = await getUser(response[chat].user2)
      console.log(user1)
      console.log(user2)
      if(context.user.id == user1.id)
        newChats.push({id:response[chat].id,key:response[chat].id,user:user2})
      else
        newChats.push({id:response[chat].id,key:response[chat].id,user:user1})
    }
    setChats(newChats)
  }
  
  const getUser = async function(url){
    //Esto va a cambiar por el tema de que ahora nos viene el id no la url
    return (await fetch(REQUEST_URI+'/'+url.replace('api/mock/',''))).json();
  }


  useEffect(()=>{
    getChats()
  },[])

  return (
    <View style={styles.container}>
      <FlatList 
        numColumns={1}
        keyExtractor={(item) => item.id} 
        data={chats} 
        renderItem={({ item }) => ( 
            <Chat user={item.user} navigation={navigation} sreen={'Room'}/>
        )}
      />

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    justifyContent: 'center'
  }
});
