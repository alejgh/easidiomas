import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList, ActivityIndicator } from 'react-native';
import Chat from './items/Chat';

export default function DiscoverScreen(props) {

  const context = useContext(AppContext);
  const {REQUEST_URI} = context.CONFIG;

  const {parentNavigation} = props;

  const [results, setResults] = useState([]);
  const [links,setLinks] = useState([]);
  const [loading,setLoading] = useState(false);

  const loadResults = async function(url,loadAnimation = true){
    if(loadAnimation)
      setLoading(true)
    let response = await (await fetch(REQUEST_URI+url,{
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token':context.token
      }
    })).json();
    console.log(response)
    //setResults([...results,response.users]) -> I´m not sure why this is not working...

    // TODO
    // SACAR EL USUARIO QUE ES EL QUE ESTÁ LOGUEADO

    setResults(results.concat(response?.users))
    setLinks(response.links)
    if(loadAnimation)
      setLoading(false)
  }

  const loadNext = function(){
    if(links.next)
      loadResults(links.next,false)
  }


  useEffect(()=>{
    loadResults('/users');
  },[])

  return (
      <View style={styles.container}>
        {loading ? 
         <ActivityIndicator  size="large" color="#fff"/>
         :
        <FlatList 
          numColumns={1}
          onEndReachedThreshold={0.01}
          onEndReached={info => {
            loadNext();
          }}
          keyExtractor={(item) => item.id} 
          data={results} 
          renderItem={({ item }) => ( 
              <Chat user={item} navigation={parentNavigation} sreen={'Profile'}/>
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
