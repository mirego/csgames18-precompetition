//
//  NotImplementedView.swift
//  Sherbook
//
//  Created by Hugo Lefrancois on 2017-10-13.
//  Copyright © 2017 Mirego. All rights reserved.
//

import UIKit

class NotImplementedView: UIView {

    private let label = UILabel()

    init() {
        super.init(frame: .zero)
        backgroundColor = .silver

        label.setProperties(text: "À implémenter...", font: UIFont.systemFont(ofSize: 16, weight: .regular), textColor: .almostBlack, fit: true)
        addSubview(label)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        label.setPosition(.positionCenters)
    }
}
