import React, { useState } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Post from './items/Post';
import randomWords from 'random-words'

export default function Home() {
  const [people, setPeople] = useState([
    { name: 'shaun', id: '1' },
    { name: 'yoshi', id: '2' },
    { name: 'mario', id: '3' },
    { name: 'luigi', id: '4' },
    { name: 'peach', id: '5' },
    { name: 'toad', id: '6' },
    { name: 'bowser', id: '7' },
    { name: 'shaun', id: '8' },
    { name: 'yoshi', id: '9' },
    { name: 'mario', id: '10' },
    { name: 'luigi', id: '11' },
    { name: 'peach', id: '12' },
    { name: 'toad', id: '13' },
    { name: 'bowser', id: '14' },
  ]);


  return (
    <View style={styles.container}>

      <FlatList 
        numColumns={1}
        keyExtractor={(item) => item.id} 
        data={people} 
        renderItem={({ item }) => ( 
            <Post name={item.name} handle={"@"+item.name} post={randomWords({min: 18, max: 40}).join(" ")}/>
        )}
      />

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#1b2836',
    justifyContent: 'center',
  },
  item: {
    flex: 1,
    marginHorizontal: 10,
    marginTop: 24,
    padding: 30,
    backgroundColor: '#1b2836',
    fontSize: 24,
  },
});
