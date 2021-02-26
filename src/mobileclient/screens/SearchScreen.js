import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Chat from './items/Chat';

export default function SearchScreen(props) {

  const context = useContext(AppContext);

  const {parentNavigation} = props;

  const [results, setResults] = useState([]);

  const getResults = async function(){
    let response = await (await fetch('http://localhost:5000/api/mock/search')).json();
    let newChats = [];
    for(let chat in response){
      let user1 = await getUser(response[chat].user1)
      let user2 = await getUser(response[chat].user2)
      if(context.user.username == user1.username)
        newChats.push({id:response[chat].id,key:response[chat].id,user:user2})
      else
        newChats.push({id:response[chat].id,key:response[chat].id,user:user1})
    }
    setResults(newChats)
  }
  
  const getUser = async function(url){
    return (await fetch('http://localhost:5000/'+url)).json();
  }


  useEffect(()=>{
    getResults()
  },[])

  return (
    <View style={styles.container}>
      <FlatList 
        numColumns={1}
        keyExtractor={(item) => item.id} 
        data={results} 
        renderItem={({ item }) => ( 
            <Chat user={item.user} navigation={parentNavigation} sreen={'Profile'}/>
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
