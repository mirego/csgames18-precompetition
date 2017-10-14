//
//  PostViewCell.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit
import AlamofireImage

class PostViewCell: UICollectionViewCell {
    static let reuseIdentifier = "PostViewCell"

    private static let sideMargin: CGFloat = 12
    private static let bigVerticalMargin: CGFloat = 17
    private static let smallVerticalMargin: CGFloat = 5
    private static let imageHeight: CGFloat = 234

    private let title = UILabel()
    private let message = UILabel()
    private let image = UIImageView()

    override init(frame: CGRect) {
        super.init(frame: frame)

        backgroundColor = .white
        layer.cornerRadius = 4

        title.setProperties(font: .systemFont(ofSize: 14, weight: .bold), textColor: .almostBlack)
        addSubview(title)

        message.setProperties(font: .systemFont(ofSize: 12, weight: .medium), textColor: .almostBlack, multiline: true)
        addSubview(message)

        image.contentMode = .scaleAspectFill
        image.clipsToBounds = true
        addSubview(image)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        let maxTextWidth = width - 2 * PostViewCell.sideMargin
        title.setPosition(.positionTopLeft, margins: .margins(top: PostViewCell.bigVerticalMargin, left: PostViewCell.sideMargin), size: CGSize(width: min(title.width, maxTextWidth), height: title.height))
        message.setRelativePosition(.relativePositionUnderAlignedLeft, toView: title, margins: .top(PostViewCell.smallVerticalMargin), fitSize: CGSize(width: maxTextWidth, height: height))
        image.setPosition(.positionBottomLeft, size: CGSize(width: width, height: PostViewCell.imageHeight))
    }

    func configure(postViewModel: PostViewModel) {
        title.setProperties(text: postViewModel.title, fit: true)
        message.setProperties(text: postViewModel.message)

        image.af_cancelImageRequest()
        if let imageUrl = postViewModel.imageUrl, let url = URL(string: imageUrl) {
            image.af_setImage(withURL: url)
        }
        setNeedsLayout()
    }

    func heightForWidth(_ width: CGFloat, postViewModel: PostViewModel) -> CGFloat {
        configure(postViewModel: postViewModel)
        var height: CGFloat  = PostViewCell.bigVerticalMargin
        height += title.height + PostViewCell.smallVerticalMargin
        height += message.sizeThatFits(CGSize(width: width - 2 * PostViewCell.sideMargin, height: CGFloat.max)).height
        height += PostViewCell.bigVerticalMargin
        if postViewModel.imageUrl != nil {
            height += PostViewCell.imageHeight
        }

        return height
    }
}
