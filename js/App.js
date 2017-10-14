import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
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
      currentPageIndex: 0,
    };
    this._swiper = null;
  }

  setSwiper(_swiper) {
    this._swiper = _swiper;
  }

  setCurrentPage(index) {
    this.setState(previousState => {
      return { currentPageIndex: index };
    });
  }

  changePage(title) {
    const index = PAGES.indexOf(title);
    const newIndex = index - this.state.currentPageIndex;
    this.setCurrentPage(newIndex);
    this._swiper.scrollBy(newIndex);
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
          <Swiper index={this.props.currentPageIndex} ref={(_swiper) => { this._swiper = _swiper; }} onIndexChanged={(index) => this.setCurrentPage(index)} loop={true} showsPagination={false}>
            <Feed />
            <Contacts />
            <Messages />
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
