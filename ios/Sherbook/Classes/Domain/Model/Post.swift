//
//  Post.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

struct Post: Codable {
    let _id: String
    let date: String?
    let author: String?
    let message: String?
    let attachment: Attachment?
}
