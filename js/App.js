import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
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
      <View style={styles.container}>
        <Header>
          <NavMenu
            pages={PAGES}
            current={PAGES[this.state.currentPageIndex]}
            onPageSelected={(title) => this.changePage(title)}
          />
        </Header>
        <Content>
          <Feed />
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
