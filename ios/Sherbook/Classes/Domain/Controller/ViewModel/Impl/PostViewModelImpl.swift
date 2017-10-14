//
//  PostViewModel.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class PostViewModelImpl: PostViewModel {

    var title: String? {
        return post.author
    }

    var message: String? {
        return post.message
    }

    var imageUrl: String? {
        if let attachment = post.attachment {
            return attachment.url
        } else {
            return nil
        }
    }

    private let post: Post

    init(post: Post) {
        self.post = post
    }

}
