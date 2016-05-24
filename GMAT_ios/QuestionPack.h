//
//  QuestionPack.h
//  GMAT
//
//  Created by Do Ngoc Trinh on 5/24/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>

@class QuestionID;

NS_ASSUME_NONNULL_BEGIN

@interface QuestionPack : NSManagedObject

// Insert code here to declare functionality of your managed object subclass
+ (instancetype)createQuestionPackWithJson:(NSDictionary *)jsonDict;
@end

NS_ASSUME_NONNULL_END

#import "QuestionPack+CoreDataProperties.h"
