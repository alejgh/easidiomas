import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Chat from './items/Chat';

export default function DiscoverScreen(props) {

  const context = useContext(AppContext);
  const {REQUEST_URI} = context.CONFIG;

  const {parentNavigation} = props;

  const [results, setResults] = useState([]);
  const [links,setLinks] = useState([]);

  const loadResults = async function(url){
    if(links?.next =='url_pagina_siguiente')  // A ver como es esto en verdad (cuando no hay más que pone ahí?)
      return;
    let response = await (await fetch(REQUEST_URI+url)).json();
    //setResults([...results,response.users]) -> I´m not sure why this is not working...
    console.log(response)
    setResults(results.concat(response?.users))
    setLinks(response.links)
  }


  useEffect(()=>{
    loadResults('/search');
  },[])

  return (
      <View style={styles.container}>
        <FlatList 
          numColumns={1}
          onEndReachedThreshold={0.01}
          onEndReached={info => {
            loadResults(links.next);
          }}
          keyExtractor={(item) => item.id} 
          data={results} 
          renderItem={({ item }) => ( 
              <Chat user={item} navigation={parentNavigation} sreen={'Profile'}/>
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
