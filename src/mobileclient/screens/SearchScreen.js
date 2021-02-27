import React, { useEffect, useState, useContext } from 'react';
import {AppContext} from '../App';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Chat from './items/Chat';

export default function SearchScreen(props) {

  const context = useContext(AppContext);

  const {parentNavigation} = props;

  const [results, setResults] = useState([]);
  const [links,setLinks] = useState([]);

  const getResults = async function(url){
    let response = await (await fetch('http://localhost:5000/api/mock'+url)).json();
    //setResults([...results,response.users]) -> I´m not sure why this is not working...
    setResults(results.concat(response.users))
    setLinks(response.links)
  }
  
  const loadMoreResults = async info => {
    getResults(links.next);
  }

  useEffect(()=>{
    getResults('/search');
  },[])

  return (
    <View style={styles.container}>
      <FlatList 
        numColumns={1}
        onEndReachedThreshold={0.01}
        onEndReached={info => {
          loadMoreResults(info);
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
