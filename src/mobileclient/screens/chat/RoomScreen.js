import React, { useState,useEffect, useContext } from 'react';
import {AppContext} from '../../App';
import { GiftedChat } from 'react-native-gifted-chat';
import { StyleSheet,SafeAreaView } from 'react-native';

export default function RoomScreen(props) {

    const context = useContext(AppContext);
    const {REQUEST_URI} = context.CONFIG;
    const {navigation,parentNavigation} = props;
    const {user,chatId} = props.route.params;
 
    const [messages, setMessages] = useState([]);

    const getMessages = async function(){
      console.log(props.route)
      let response = await (await fetch(REQUEST_URI+'/chats/'+chatId+'/messages',{
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'token':context.token
        }})).json();
      let newMessages = [];
  
      for(let msg in response){
        let sender = await getUser(response[msg].sender)
        if(sender.id == context.user.id){
          newMessages.push({
            _id: response[msg].id,
            text: response[msg].text,
            createdAt: new Date(response[msg].createdAt).getTime(),
            user: {
              _id: 1
            }
          })
        }else{
          newMessages.push({
            _id: response[msg].id,
            text: response[msg].text,
            createdAt: new Date(response[msg].createdAt).getTime(),
            user: {
              _id: 2,
              name: user.name,
              avatar: user.avatar.replace('https','http')
            }
          })
        }
        
      }
      console.log(newMessages)
      setMessages(newMessages.reverse());
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
        getMessages()
      });
  
      return unsubscribe;
    }, [navigation]);

    useEffect(()=>{
        parentNavigation.setOptions({
            tabBarVisible: false
        });


        getMessages();

        return () => {
            parentNavigation.setOptions({
                tabBarVisible: true
            });
        }
    },[])


  // helper method that is sends a message
  async function handleSend(newMessage = []) {
    setMessages(GiftedChat.append(messages, newMessage));
    let response = await fetch(REQUEST_URI+'/chats/'+chatId+'/messages',{
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token':context.token
      },
      body: JSON.stringify({text:newMessage[0].text})
    });

    console.log(response.status)
    getMessages();
  }

  return (
    <SafeAreaView style={ styles.container }>
      <GiftedChat
        messages={messages}
        onSend={newMessage => handleSend(newMessage)}
        user={{ _id: 1 }}
      />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    justifyContent: 'center'
  }
});
