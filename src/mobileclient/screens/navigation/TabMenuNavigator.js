import * as React from 'react';
import { Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons'
import MessagesStackNavigator from './MessagesStackNavigator';
import SearchScreen from '../SearchScreen';
import HomeStackNavigator from './HomeStackNavigator';


const Tab = createBottomTabNavigator();

  
export  function TabMenuNavigator({ navigation }) {
    return (
    <NavigationContainer independent  >
        <Tab.Navigator initialRouteName="Search" screenOptions={({ route }) => ({
          tabBarIcon: ({ focused, color, size }) => {
            let iconName;

            if (route.name === 'Home') {
              iconName = focused ? 'home' : 'home-outline';
            } else if (route.name === 'Search') {
              iconName = focused ? 'search' : 'search-outline';
            }
            else if (route.name === 'Mesagges') {
                iconName = focused ? 'mail' : 'mail-outline';
            }
            else if (route.name === 'Profile') {
                iconName = focused ? 'person' : 'person-outline';
            }

            // You can return any component that you like here!
            return <Ionicons name={iconName} size={size} color={color} />;
          },
        })}
        animationEnabled
        tabBarOptions={{
          showIcon: true,
          showLabel:false,
          activeBackgroundColor: '#1b2836',
          inactiveBackgroundColor: '#1b2836',
          iconStyle:{
            marginBottom:5,
            marginTop:5
          },
          tabStyle: {
              justifyContent: 'center',
              alignItems: 'center'
          },
          indicatorStyle: {
            backgroundColor: 'transparent',
          },
          style: {
            borderTopWidth: 1,
            borderTopColor: '#435060'
          },
        }}
      >
          <Tab.Screen name="Home" component={HomeStackNavigator} />
          <Tab.Screen name="Search" component={SearchScreen} />
          <Tab.Screen name="Mesagges" component={MessagesStackNavigator} />
          <Tab.Screen name="Profile" component={HomeStackNavigator} />
        </Tab.Navigator>
    </NavigationContainer>
    );
  }
  