import  React, {useEffect, useState} from 'react';
import { NavigationContainer } from '@react-navigation/native';
import TabMenuNavigator from './screens/navigation/AppTabNavigator';
import {  StyleSheet } from 'react-native';
import AuthStackNavigator from './screens/navigation/AuthStackNavigator';

export const AppContext = React.createContext();

export default function App() {

  const [user,setUser] = useState(null); //Poner a null

  return (
    <AppContext.Provider value={{user:user,setUser:setUser}}>
        <NavigationContainer  initialRouteName="Login">
            {user ? <TabMenuNavigator/>: <AuthStackNavigator/>}
      </NavigationContainer>
    </AppContext.Provider>
  );
}
const styles = StyleSheet.create({
  spinnerTextStyle: {
    color: '#FFF'
  }})


  export const DefaultUser = {
    id: 99,
    name: 'Pablo',
    surname: 'Menéndez Suárez',
    username: '@mistermboy',
    learning: ['en', 'cn'],
    speaks: 'es',
    birthDate: '<long_time_since_epoch>',
    avatar: 'https://bootdey.com/img/Content/avatar/avatar2.png'
  }
    