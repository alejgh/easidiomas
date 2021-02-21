import React, { useState,useEffect } from 'react';
import { View, Text} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import MessagesScreen from '../MessagesScreen';

function HomeScreen() {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Home Screen</Text>
    </View>
  );
}

export default function SearchTabNavigator({navigation}){

    const Tab = createMaterialTopTabNavigator();

    return(
    <NavigationContainer independent  initialRouteName="For you">
        <Tab.Navigator 

        tabBarOptions={{
          activeTintColor: '#1DA1F2',
          inactiveTintColor: '#435060',
          labelStyle: { 
            fontSize: 12            
          },
          tabStyle: { 
            height: 50
          },
          style: { 
            backgroundColor: '#1b2836'
          },
        }} >
          <Tab.Screen name="For you" component={MessagesScreen} />
          <Tab.Screen name="Languaje" component={MessagesScreen} />
          <Tab.Screen name="Age" component={MessagesScreen} />
        </Tab.Navigator>
    </NavigationContainer>
    )
}
