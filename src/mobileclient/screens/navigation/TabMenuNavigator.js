import * as React from 'react';
import { Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import FontAwesome from 'react-native-vector-icons/FontAwesome'
import Ionicons from 'react-native-vector-icons/Ionicons'
import HomeScreen from '../HomeScreen';



const Tab = createBottomTabNavigator();

  function SettingsScreen() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Text>Settings!</Text>
      </View>
    );
  }
  

export  function TabMenuNavigator({ navigation }) {
    return (
    <NavigationContainer independent>
        <Tab.Navigator screenOptions={({ route }) => ({
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
            showIndicator:false,
            titleStyle: {
                justifyContent: 'center',
                alignItems: 'center',
            },
            style: {
                borderWidth: 0,
                position:'absolute',
                bottom:0,
                left:0,
                width:'100%',
                backgroundColor: 'rgb(27, 42, 51)',
                borderColor: 'rgb(27, 42, 51)',
                shadowColor:'red',
                elevation:2
            },
            activeBackgroundColor: 'rgb(27, 42, 51)',
            inactiveBackgroundColor: 'rgb(27, 42, 51)',
            labelStyle: {
                fontSize: 14,
                color: '#fff',
                position: 'relative',
                alignSelf: 'center',
        
            },
            iconStyle:{
              marginBottom:5,
              marginTop:5
            },
            tabStyle: {
                justifyContent: 'center',
                alignItems: 'center',
                
            },
            indicatorStyle: {
              backgroundColor: 'transparent',
          },
        }}
      >
          <Tab.Screen name="Home" component={HomeScreen} />
          <Tab.Screen name="Search" component={HomeScreen} />
          <Tab.Screen name="Mesagges" component={HomeScreen} />
          <Tab.Screen name="Profile" component={HomeScreen} />
        </Tab.Navigator>
    </NavigationContainer>
    );
  }
  