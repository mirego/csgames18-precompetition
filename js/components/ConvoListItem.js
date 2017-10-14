import React from 'react';
import { TouchableHighlight, StyleSheet, Text, View, Image, Dimensions } from 'react-native';

export default class ConvoListItem extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      author: props.author,
      messages: props.messages,
      isSelected: true
    };
  }

  render() {
    if (this.state.isSelected) {
      let style = styles.messageContainerFull;
      return (
        <TouchableHighlight
          underlayColor="white"
          onPress={() => this.setState({ ...this.state, isSelected: !this.state.isSelected})}
          key={this.state.author}
          style={style}
        >
          <View>
            {this.state.messages.map((message) =>
              <View style={style}>
                <Text style={styles.title}>{message.senderName}: </Text>
                <Text style={styles.lastMessage}>{message.message}</Text>
              </View>
            )}
          </View>
        </TouchableHighlight>
      );
    } else {
      let style = styles.messageContainer;
      return (
        <TouchableHighlight
          underlayColor="white"
          onPress={() => this.setState({ ...this.state, isSelected: !this.state.isSelected})}
          key={this.state.author}
          style={style}
        >
          <View style={style}>
            <Text style={styles.title}>{this.state.author}: </Text>
            <Text style={styles.lastMessage}>{this.state.messages[0].message}</Text>
          </View>
        </TouchableHighlight>
      );
    }




  }
}

const styles = StyleSheet.create({
  messageContainerFull: {
    flexDirection: 'row',
    padding: 10,
    backgroundColor: 'white',
  },
  messageContainer: {
    flexDirection: 'row',
    marginBottom: 8,
    padding: 10,
    backgroundColor: 'white',
  },
  title: {
    paddingLeft: 10,
    marginBottom: 5,
    fontWeight: 'bold',
    color: 'black'
  },
  lastMessage: {
    alignSelf: 'flex-end',
    paddingLeft: 10,
    marginBottom: 5,
    color: 'grey'
  }
});
