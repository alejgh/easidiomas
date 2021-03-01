import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList,ActivityIndicator } from 'react-native';
import Chat from './items/Chat';

export default function ChatsScreen({route,navigation}) {

  const context = useContext(AppContext);
  const {REQUEST_URI} = context.CONFIG;
  const [chats, setChats] = useState([]);
  const [loading,setLoading] = useState(false);

  const getChats = async function(){
    setLoading(true)
    let response = await (await fetch(REQUEST_URI+'/chats',{
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token':context.token
      }})).json();

    let newChats = [];
    for(let chat in response){
      let user1 = await getUser(response[chat].user1)
      let user2 = await getUser(response[chat].user2)
      if(context.user.id == user1.id)
        newChats.push({id:response[chat].id,key:response[chat].id,user:user2})
      else
        newChats.push({id:response[chat].id,key:response[chat].id,user:user1})
    }
    setChats(newChats)
    setLoading(false)
  }
  
  const getUser = async function(id){
    return (await fetch(REQUEST_URI+'/users/'+id,{
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token':context.token
      }})).json();
  }

  useEffect(() => {
    const unsubscribe = navigation.addListener('focus', () => {
      getChats()
    });

    return unsubscribe;
  }, [navigation]);

  useEffect(()=>{
    getChats()
  },[])

  return (
    <View style={styles.container}>
      {loading ? 
        <ActivityIndicator  size="large" color="#fff"/>
        :
      <FlatList 
        numColumns={1}
        keyExtractor={(item) => item.id} 
        data={chats} 
        renderItem={({ item }) => ( 
            <Chat user={item.user} chatId={item.id} navigation={navigation} sreen={'Room'}/>
        )}
      />
        }
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
