//
//  HomeController.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import Foundation

protocol HomeController {
    func getPosts(completion: @escaping (_ posts: [PostViewModel]?, _ error: Error?) -> ())
}
