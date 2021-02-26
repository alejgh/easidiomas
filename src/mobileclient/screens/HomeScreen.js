import React, { useState,useEffect } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Post from './items/Post';
import randomWords from 'random-words'
import { FloatingAction } from "react-native-floating-action";
import Ionicons from 'react-native-vector-icons/Ionicons'

export default function Home(props) {
  
  const{parentNavigation,navigation} = props;

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

  const actions = [
    {
      text: "Accessibility",
      name: "bt_accessibility",
      icon: <Ionicons name={'add-outline'}  size={25} color={'#fff'} />,
      position: 1
    },]

  return (
    <View style={styles.container}>

      <FlatList 
        numColumns={1}
        keyExtractor={(item) => item.id} 
        data={people} 
        renderItem={({ item }) => ( 
            <Post parentNavigation={parentNavigation} name={item.name} handle={"@"+item.name} post={randomWords({min: 18, max: 40}).join(" ")}/>
        )}
      />

      <FloatingAction
          color={'#1DA1F2'}
          animated={false}
          actions={actions}
          overrideWithAction
          onPressItem={name => {
            navigation.navigate("New Post");
          }}
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
