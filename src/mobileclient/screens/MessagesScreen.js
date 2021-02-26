import React, { useState } from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import Conversation from './items/Conversation';

export default function MessagesScreen({navigation}) {
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
            <Conversation name={item.name} handle={"@"+item.name} navigation={navigation}/>
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
