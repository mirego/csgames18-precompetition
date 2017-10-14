import React from 'react';
import { StyleSheet, Text, View, FlatList } from 'react-native';
import ContactListItem from './ContactListItem';

export default class Contacts extends React.Component {
  constructor(props) {
    super(props);
    this.state  = {
      contacts: [
        {
          firstName: "Johnson",
          lastName: "Anderson"
        }, {
          firstName: "Arnold",
          lastName: "Anderson"
        }
      ]
    };
  }

  _renderContacts = ({ item }) => (
    <ContactListItem contact={item} />
  );

  render() {
    return (
      <View>
        <FlatList
          data={this.state.contacts}
          renderItem={this._renderContacts}
        />
      </View>
    );
  }
}
