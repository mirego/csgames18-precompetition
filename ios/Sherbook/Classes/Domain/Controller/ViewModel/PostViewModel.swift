//
//  PostViewModel.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

protocol PostViewModel {
    var title: String? { get }
    var message: String? { get }
    var imageUrl: String? { get }
}
