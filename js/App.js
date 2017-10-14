import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Swiper from 'react-native-swiper';

import Header from './containers/Header';
import Content from './containers/Content';
import NavMenu from './components/NavMenu';
import Feed from './components/Feed';
import Messages from './components/Messages';
import Contacts from './components/Contacts';
import Settings from './components/Settings';

export default class App extends React.Component {
  render() {
    return (
      <View style={ styles.container }>
        <Header>
          <NavMenu />
        </Header>
        <Content>
          <Swiper showsPagination={false} onIndexChange={(index) => { this.index = 0 } } >
            <Feed />
            <Messages />
            <Contacts />
            <Settings />
          </Swiper>
        </Content>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1
  },
});
