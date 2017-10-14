//
//  PostButton.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-01-20.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class PostButton: UIButton {
    private let icon = UIImageView(image: UIImage(named: "icnPlus"))

    override var isHighlighted: Bool {
        didSet {
            backgroundColor = isHighlighted ? .emeraldHighlighted : .emerald
        }
    }

    init() {
        super.init(frame: .zero)

        backgroundColor = .emerald
        addSubview(icon)

        size = CGSize(width: 63, height: 63)
        layer.cornerRadius = height / 2
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        icon.setPosition(.positionCenters)
    }
}
