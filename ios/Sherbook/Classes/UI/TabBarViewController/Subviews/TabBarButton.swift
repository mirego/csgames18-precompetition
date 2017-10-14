//
//  TabBarButton.swift
//  XMedius
//
//  Created by Hugo Lefrancois on 2017-01-20.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit
import MRGTaylor

class TabBarButton: UIButton {
    private let icon: UIImageView
    private let tabLabel = UILabel()

    override var isHighlighted: Bool {
        didSet {
            updateIconColor()
        }
    }

    override var isSelected: Bool {
        didSet {
            updateIconColor()
        }
    }

    init(iconName: String, label: String) {
        icon = UIImageView(image: UIImage(named: iconName))

        super.init(frame: .zero)

        icon.tintColor = .emerald
        addSubview(icon)

        tabLabel.setProperties(text: label, font: .systemFont(ofSize: 10, weight: .regular), textColor: .warmGrey, highlightedTextColor: .emerald, fit: true)
        addSubview(tabLabel)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        tabLabel.setPosition(.positionBottomHCenter, margins: .bottom(4))

        let topMargin = (tabLabel.minY - icon.height) / 2
        icon.setPosition(.positionTopHCenter, margins: .top(topMargin))
    }

    private func updateIconColor() {
        let isActive = isHighlighted || isSelected
        icon.tintColor = isActive ? .emerald : .warmGrey
        tabLabel.isHighlighted = isActive
    }
}
