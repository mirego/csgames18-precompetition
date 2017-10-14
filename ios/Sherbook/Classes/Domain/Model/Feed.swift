//
//  Feed.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

struct Feed: Codable {
    let feed: String
    let lastUpdate: String
    let posts: [Post]?
}
