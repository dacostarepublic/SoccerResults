import unittest
import os
import collections
from soccerResults import process_file, parse_line

class TestSoccerResults(unittest.TestCase):
    # Positive test cases
    def test_process_file_readable_file_exists(self):
        # When the file exists and is readable
        with open('temp.txt', 'w') as f:
            f.write('Team1 3, Team2 2\n')
            f.write('Team3 1, Team4 1\n')
        expected = collections.defaultdict(int, {'Team1': 3, 'Team2': 0, 'Team3': 1, 'Team4': 1})
        result = process_file('temp.txt')
        self.assertEqual(result, expected)
        os.remove('temp.txt')

    def test_parse_line_positive(self):
        line = 'Team1 3, Team2 2'
        expected = ('Team1', 3, 'Team2', 2)
        result = parse_line(line)
        self.assertEqual(result, expected)

    # Negative test cases
    def test_process_file_does_not_exist(self):
        # When the file does not exist
        result = process_file('nonexistent.txt')
        expected = collections.defaultdict(int)
        self.assertEqual(result, expected)
    
    def test_process_file_is_empty(self):
        # When the file exists and is empty
        with open('empty.txt', 'w') as f:
            pass
        result = process_file('empty.txt')
        expected = collections.defaultdict(int)
        self.assertEqual(result, expected)
        os.remove('empty.txt')

    def test_process_file_score_missing(self):
        # When the first team's score is missing
        with open('missing.txt', 'w') as f:
            f.write('Team1 3, Team2 2\n')
            f.write('Team3, Team4 2\n')
            f.write('Team1 1, Team4 1\n')
        expected = collections.defaultdict(int, {'Team1': 4, 'Team2': 0, 'Team4': 1})
        result = process_file('missing.txt')
        self.assertEqual(result, expected)
        os.remove('missing.txt')
        # When the second team's score is missing
        with open('missing.txt', 'w') as f:
            f.write('Team1 3, Team2 2\n')
            f.write('Team3 1, Team4\n')
        expected = collections.defaultdict(int, {'Team1': 3, 'Team2': 0})
        result = process_file('missing.txt')
        self.assertEqual(result, expected)
        os.remove('missing.txt')
        
if __name__ == '__main__':
    unittest.main()