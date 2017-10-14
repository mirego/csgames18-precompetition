import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Swiper from 'react-native-swiper';

import Header from './containers/Header';
import Content from './containers/Content';
import NavMenu from './containers/NavMenu';
import Feed from './components/Feed';

const PAGES = [
  'Home',
  'Friends',
  'Messages',
  'Settings'
]

import Messages from './components/Messages';
import Contacts from './components/Contacts';
import Settings from './components/Settings';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentPageIndex: 0
    };
  }

  changePage(title) {
    this.setState(previousState => {
      return {currentPageIndex: PAGES.indexOf(title)};
    });
  }

  render() {
    return (
      <View style={ styles.container }>
        <Header>
          <NavMenu
            pages={PAGES}
            current={PAGES[this.state.currentPageIndex]}
            onPageSelected={(title) => this.changePage(title)}
          />
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
