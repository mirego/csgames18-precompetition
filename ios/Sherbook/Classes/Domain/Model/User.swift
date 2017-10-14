//
//  User.swift
//  Sherbook
//
//  Created by pgirard on 2017-10-14.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

struct User: Codable {
    let _id: String
    let username: String
    let url: String
    let token: String
}
