//
//  Answer+CoreDataProperties.h
//  GMAT
//
//  Created by Trung Đức on 4/9/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

#import "Answer.h"

NS_ASSUME_NONNULL_BEGIN

@interface Answer (CoreDataProperties)

@property (nullable, nonatomic, retain) NSNumber *index;
@property (nullable, nonatomic, retain) NSString *choice;
@property (nullable, nonatomic, retain) NSString *explanation;
@property (nullable, nonatomic, retain) NSString *note;
@property (nullable, nonatomic, retain) Question *question;

@end

NS_ASSUME_NONNULL_END
