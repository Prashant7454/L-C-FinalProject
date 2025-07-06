-- Add isHide column to category table
ALTER TABLE category ADD COLUMN isHide INTEGER NOT NULL DEFAULT 0;

-- Update existing categories to have isHide = 0 (visible)
UPDATE category SET isHide = 0 WHERE isHide IS NULL; 