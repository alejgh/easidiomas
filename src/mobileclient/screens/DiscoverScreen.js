import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList, ActivityIndicator } from 'react-native';
import Chat from './items/Chat';
import { nativeViewProps } from 'react-native-gesture-handler/dist/src/handlers/NativeViewGestureHandler';

export default function DiscoverScreen(props) {

  const appContex = useContext(AppContext);
  
  const {REQUEST_URI} = appContex.CONFIG;

  const {navigation,parentNavigation,getFilters,native,setNative,learning1,setLearning1,learning2,setLearning2,minAge,maxAge,setMinAge,setMaxAge} = props;
  const {route} = props;

  const [results, setResults] = useState([]);
  const [links,setLinks] = useState([]);
  const [loading,setLoading] = useState(false);
  const [first,setFirst] = useState(true);
  const [filters,setFilters] = useState(null);

  const loadResults = async function(url){
    if(first)
      setLoading(true)

    let response = await (await fetch(REQUEST_URI+url,{
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'token':appContex.token
      }
    })).json();


    let newUsers = [];
    for(let usr in response.users){
      console.log('TODOOOOOO')
      console.log(response.users[usr].role)
      if(response.users[usr].role!=1){
        newUsers.push(response.users[usr])
      }
    }
    //setResults([...results,response.users]) -> I´m not sure why this is not working...

    // TODO
    // SACAR EL USUARIO QUE ES EL QUE ESTÁ LOGUEADO

    setResults(results.concat(newUsers))
    setLinks(response.links)
    if(first)
      setLoading(false)
  }


  const loadNext = function(){
    if(links.next)
      loadResults(links.next)
  }

  
  useEffect(() => {
    const unsubscribe = navigation.addListener('focus', () => {
      console.log(route?.params?.filters)
      setFilters(route?.params?.filters);
      loadResults('/users');
    });

    return unsubscribe;
  }, [route,navigation]);


  useEffect(() => {
    const unsubscribe = navigation.addListener('focus', () => {
      loadResults('/users');
      setFirst(false);
    });

    return unsubscribe;
  });


  useEffect(()=>{
    loadResults('/users');
    setFirst(false);
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
