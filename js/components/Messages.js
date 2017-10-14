import React from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import ConvoListItem from './ConvoListItem';

export default class Messages extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      groupedMessages: [
        {
          author: "Johnson",
          messages: [
            "Allo"
          ]
        }, {
          author: "Arnold",
          messages: [
            "Sup",
            "How you been?"
          ]
        },
      ]
    };
  }

  _renderMessages = ({ item }) => (
    <ConvoListItem author={item.author} messages={item.messages} />
  );

  render() {
    return (
      <View>
        <FlatList
          data={this.state.groupedMessages}
          renderItem={this._renderMessages}
        />
      </View>
    );
  }
}
