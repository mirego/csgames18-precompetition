//
//  Time.swift
//
//  Copyright (c) 2015 Sagemcom. All rights reserved.
//

import Foundation

/*
    From http://stackoverflow.com/questions/24034544/dispatch-after-gcd-in-swift/24318861#24318861
    Example use, switching light on after 2 seconds:
    delay(2) {
        println("📱👈 -> 💡")
        light.switchOn()
    }
*/
func delay(_ delay: Double, closure:@escaping () -> (Void)) {
    let time = DispatchTime.now() + Double(Int64(delay * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
    DispatchQueue.main.asyncAfter(deadline: time, execute: closure)
}
